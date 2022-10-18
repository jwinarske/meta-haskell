DESCRIPTION = "The Glorious Glasgow Haskell Compilation System"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=7cb08deb79c4385547f57d6bb2864e0f"

SRC_URI = "https://downloads.haskell.org/~${BPN}/${PV}/${BPN}-${PV}-x86_64-deb10-linux.tar.xz"
SRC_URI[md5sum] = "68e62b2c9602761681a4b1eb2a2420c0"
SRC_URI[sha256sum] = "c253e7eb62cc9da6524c491c85ec8d3727c2ca6035a8653388e636aaa30a2a0f"

DEPENDS = "\
    compiler-rt \
    gmp \
    libcxx \
    ncurses \
"

S = "${WORKDIR}/${BPN}-${PV}"

inherit native autotools-brokensep haskell

RUNTIME = "llvm"
TOOLCHAIN = "clang"
PREFERRED_PROVIDER:libgcc = "compiler-rt"

#EXTRA_OECONF += "" 
CLEANBROKEN = "1"

do_configure() {

    cd ${S}
    echo "HADDOCK_DOCS = NO" > ${S}/mk/build.mk
    echo "WITH_TERMINFO = NO" >> ${S}/mk/build.mk

    ./configure CC=${CC} CXX=${CXX} --prefix=${prefix}
}

do_compile() {
}

do_install() {

    export LD_LIBRARY_PATH="${S}/libraries/terminfo/dist-install/build"
    export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:${STAGING_DIR_NATIVE}${libdir}"

    bbnote "prefix=${prefix}"

    cd ${S}
    oe_runmake DESTDIR=${D} install VERBOSE=1
    
    # Workaround needed cause ghc searches for ghcversion.h and other files in 
    # ${WORKDIR}/recipe-sysroot-native/usr/
    # cp -rv \
    # ${WORKDIR}/image/home/michael/yocto/poky-warrior/build/tmp/work/x86_64-linux/ghc-native/8.8.2-r0/recipe-sysroot-native/usr/* \
    # ${WORKDIR}/recipe-sysroot-native/usr/
#    bbnote `ls -la ${D}${prefix}`
#    ls -la ${D}${prefix}
#    cp -rv ${D}${prefix}/* ${STAGING_DIR_NATIVE}/usr/

#    export PATH=${STAGING_DIR_NATIVE}/usr/lib/${BPN}-${PV}/bin:$PATH

#    ghc-pkg --version
#    ghc-pkg list

    # init haskell database at ${TMPDIR}/haskell
#    [ -d ${HASKELL_PACKAGES} ] && rm -rf ${HASKELL_PACKAGES}
#    ghc-pkg init ${HASKELL_PACKAGES}
    
    # create your own empty database, and copy over packages from a different database
    #ghc-pkg --expand-pkgroot describe base | \
    #    ghc-pkg --global-package-db package.conf.d register -
#    ghc-pkg --global-package-db=${STAGING_DIR_NATIVE}${libdir}/${BPN}-${PV}/package.conf.d \
#        --global \
#        --expand-pkgroot describe base | \
#        ghc-pkg --global-package-db package.conf.d register -

    ls -laR ${D}
}

INSANE_SKIP:${PN} += "already-stripped"
