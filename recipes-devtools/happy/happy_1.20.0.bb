DESCRIPTION = "Happy is a parser generator for Haskell"
SECTION = "devel"
LICENSE = "BSD2"
LIC_FILES_CHKSUM ?= "file://LICENSE;md5=08116e63bfc84854907f25c24f475143"

SRC_URI = "https://hackage.haskell.org/package/${BPN}-${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "f6d214eb9f0271e327a2948ba45d1612"
SRC_URI[sha256sum] = "3b1d3a8f93a2723b554d9f07b2cd136be1a7b2fcab1855b12b7aab5cbac8868c"

S = "${WORKDIR}/${BPN}-${PV}"

inherit haskell haskell-package

BBCLASSEXTEND = "native nativesdk" 

INSANE_SKIP:${PN} += "already-stripped"

