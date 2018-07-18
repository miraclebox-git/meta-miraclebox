SSUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

KV = "4.0.1"
SRCDATE = "20170502"

PV = "${KV}+${SRCDATE}"
PR = "r0"

SRC_URI[md5sum] = "804f46aebd84f215c9977d76df73921a"
SRC_URI[sha256sum] = "b02d4b87795472cfcf275d9e3ef956dd05179de6b687a9737fe0d633bd513c69"

#SRC_URI = "file://ceryon-7000s-dvbdrive-${SRCDATE}.zip"
SRC_URI = "http://source.mynonpublic.com/ceryon/ceryon-7000s-dvbdrive-${SRCDATE}.zip"

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
