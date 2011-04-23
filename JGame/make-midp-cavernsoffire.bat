del /S /Q classes-examples-tmp >devnull
mkdir classes-examples-tmp
del /S /Q classes-examples >devnull
mkdir classes-examples

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

set APPNAME=CavernsOfFireMidlet
set MANIFESTNAME=manifests\manifest_midp_cavernsoffire

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp examples/CavernsOfFire.java -d classes-examples-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp -d classes-examples  classes-examples-tmp


mkdir classes-examples\examples\gfx
copy examples\caverns_of_fire.tbl classes-examples\examples\
copy examples\gfx\ballbullet-tr.gif classes-examples\examples\gfx\
copy examples\gfx\iceblock-16.gif classes-examples\examples\gfx\
copy examples\gfx\cavewalls.gif classes-examples\examples\gfx\
copy examples\gfx\cavetile1-sm.jpg classes-examples\examples\gfx\
copy examples\gfx\cavetile2-sm.jpg classes-examples\examples\gfx\
copy examples\gfx\cavetile3-sm.jpg classes-examples\examples\gfx\
copy examples\gfx\cavetile4-sm.jpg classes-examples\examples\gfx\
copy examples\gfx\firecavernsprites.gif classes-examples\examples\gfx\

echo "Jaring preverified class files..."
jar cmf %MANIFESTNAME% %APPNAME%.jar -C classes-examples examples -C classes-midp jgame

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir %APPNAME%.jar >b.tmp
type %MANIFESTNAME% a.tmp b.tmp >%APPNAME%.jad

