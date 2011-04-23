#!/bin/sh
#

if test $# -eq 3 ;
then
	APPPATH=$1
	PACKAGENAME=$2
	MAINCLASS=$3

	mkdir -p ${APPPATH}

	cat MyGame.java | sed s/MyGame/${MAINCLASS}/ \
		| sed "s/package mygame/package ${PACKAGENAME}/" \
		> ${APPPATH}/${MAINCLASS}.java

	cp -i *.png *.tbl ${APPPATH}
	cp -i manifest_midp_mygame ${APPPATH}/manifest_midp

	echo 'Main-class: '${PACKAGENAME}.${MAINCLASS} >${APPPATH}/manifest_jre

	cat make-midp-mygame-preamble               >${APPPATH}/make-midp
	echo JARNAME=${MAINCLASS}Midlet            >>${APPPATH}/make-midp
	echo MANIFESTNAME=${APPPATH}/manifest_midp >>${APPPATH}/make-midp
	echo APPPATH=${APPPATH}                    >>${APPPATH}/make-midp
	echo MAINCLASS=${PACKAGENAME}.${MAINCLASS} >>${APPPATH}/make-midp
	echo APPNAME=${MAINCLASS}                  >>${APPPATH}/make-midp
	cat make-midp-mygame-postamble             >>${APPPATH}/make-midp

	cat make-jre-mygame-preamble                >${APPPATH}/make-jre
	echo JARNAME=${MAINCLASS}                  >>${APPPATH}/make-jre
	echo MANIFESTNAME=${APPPATH}/manifest_jre  >>${APPPATH}/make-jre
	echo APPPATH=${APPPATH}                    >>${APPPATH}/make-jre
	echo MAINCLASS=${PACKAGENAME}.${MAINCLASS} >>${APPPATH}/make-jre
	cat make-jre-mygame-postamble              >>${APPPATH}/make-jre

else
	echo "Generate skeleton for jgame app in current directory."
	echo "Supply the following parameters:"
	echo "    path to app directory (i.e. examples/mygame)"
	echo "    package name (i.e. examples.mygame)"
	echo "    main class (i.e. MyGame)"
fi
