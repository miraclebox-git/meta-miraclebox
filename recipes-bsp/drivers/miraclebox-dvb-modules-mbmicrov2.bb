SSUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

KV = "4.0.1"
SRCDATE = "20170516"

PV = "${KV}+${SRCDATE}"
PR = "r0"

SRC_URI[md5sum] = "22a65a3f9a1cc70eed69e45bfac9ef27"
SRC_URI[sha256sum] = "1709ac9e9ee36c16dd6509c0b667684d64d6a8e0d147f46c0e4229b2b815d65e"

SRC_URI = "http://source.mynonpublic.com/ceryon/ceryon-7005s-dvbdrive-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modules-load.d
    echo "dvbdrive" >> ${D}/${sysconfdir}/modules-load.d/dvbdrive.conf
    install -m 0755 ${WORKDIR}/dvbdrive.ko ${D}/lib/modules/${KV}/extra
    install -d ${D}/etc/rcS.d
}

FILES_${PN} += "${sysconfdir}/modules-load.d/dvbdrive.conf"
