DESCRIPTION = "Common Architecture for Building Applications and Libraries"
SECTION = "devel"
LICENSE = "BSD-3"
LIC_FILES_CHKSUM ?= "file://LICENSE;md5=e576aaf6e8c02926200675138e801265"

SRC_URI = "https://hackage.haskell.org/package/Cabal-${PV}/Cabal-${PV}.tar.gz"
SRC_URI[md5sum] = "07e1b5227db96762b7cfc47717ea3493"
SRC_URI[sha256sum] = "7464cbe6c2f3d7e5d0232023a1a7330621f8b24853cb259fc89a2af85b736608"

S = "${WORKDIR}/Cabal-${PV}"

inherit haskell haskell-package

BBCLASSEXTEND = "native nativesdk" 

INSANE_SKIP:${PN} += "already-stripped"
