DESCRIPTION = "Alex is a tool for generating lexical analysers in Haskell"
SECTION = "devel"
LICENSE = "BSD-3"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://hackage.haskell.org/package/${BPN}-${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "d9a926a60b8a0950601ad3d98321ae41"
SRC_URI[sha256sum] = "9bd2f1a27e8f1b2ffdb5b2fbd3ed82b6f0e85191459a1b24ffcbef4e68a81bec"

S = "${WORKDIR}/${BPN}-${PV}"

inherit haskell haskell-package

BBCLASSEXTEND = "native nativesdk" 

FILES_${PN} += "${datadir}/*"

INSANE_SKIP:${PN} += "already-stripped"

