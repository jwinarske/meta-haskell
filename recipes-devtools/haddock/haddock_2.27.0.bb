DESCRIPTION = "Haddock is the standard tool for generating documentation from Haskell code."
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "https://hackage.haskell.org/package/${BPN}-${PV}/haddock-${PV}.tar.gz"

SRC_URI[md5sum] = "5e95e728b8631b5332170876324b00de"
SRC_URI[sha256sum] = "d6d31180960b2a6f833d196cea79573c4dff5db4f6aff2ad3d7ca705d9018224"

S = "${WORKDIR}/haddock-haddock-${PV}-release/"

inherit haskell haskell-package

BBCLASSEXTEND = "native nativesdk" 
