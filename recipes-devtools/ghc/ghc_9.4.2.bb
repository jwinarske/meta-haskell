DESCRIPTION = "The Glorious Glasgow Haskell Compilation System"
LICENSE = "GHC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7cb08deb79c4385547f57d6bb2864e0f"

DEPENDS = "\
    binutils-cross-${TARGET_ARCH} \
    compiler-rt \
    ghc-native \
    gmp \
    gcc-cross-${TARGET_ARCH} \ 
    libcxx \
    python3-native \
"

RUNTIME:class-native = "llvm"
TOOLCHAIN:class-native = "clang"
PREFERRED_PROVIDER:libgcc:class-native = "compiler-rt"

SRCREV = "e8a889a7fc670532a3bf883a3e25acba92e6e6e1"
SRC_URI = "gitsm://github.com/ghc/ghc.git;protocol=https;nobranch=1"

S = "${WORKDIR}/git"

inherit pkgconfig autotools-brokensep haskell perlnative

#EXTRA_AUTORECONF += " --exclude=aclocal "
#CLEANBROKEN = "1"

EXTRA_OECONF += " \
    --host=${BUILD_SYS} \
    --build=${BUILD_SYS} \
    --target=${TARGET_SYS} \
"

do_configure_prepend() {

    echo "HADDOCK_DOCS=NO"     > ${S}/mk/build.mk
    echo "WITH_TERMINFO=NO"   >> ${S}/mk/build.mk
    echo "Stage1Only=YES"     >> ${S}/mk/build.mk
    echo "BuildFlavour=quick" >> ${S}/mk/build.mk

#    ./boot && ./configure
#    hadrian/build -j

    ghc-pkg --version
    ghc-pkg list

    ghc --version

    cd ${S}/hadrian/bootstrap

    python3 bootstrap.py \
      -d plan-bootstrap-9_0_1.json \
      -w ${STAGING_DIR_NATIVE}${libdir}/${BPN}-${PV}/bin/ghc 
    
    export PATH=${S}/hadrian/bootstrap/_build/bin:$PATH
    hadrian --version
}
