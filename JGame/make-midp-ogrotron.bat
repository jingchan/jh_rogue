del /S /Q classes-examples-tmp >devnull
mkdir classes-examples-tmp
del /S /Q classes-examples >devnull
mkdir classes-examples

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

set APPNAME=OgrotronMidlet
set MANIFESTNAME=manifests\manifest_midp_ogrotron

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp examples/ogrotron/Ogrotron.java -d classes-examples-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp -d classes-examples  classes-examples-tmp

copy examples\ogrotron\ogrotron.tbl classes-examples\examples\ogrotron\
copy examples\ogrotron\ogrosprites.gif classes-examples\examples\ogrotron\

echo "Jaring preverified class files..."
jar cmf %MANIFESTNAME% %APPNAME%.jar -C classes-examples examples -C classes-midp jgame

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir %APPNAME%.jar >b.tmp
type %MANIFESTNAME% a.tmp b.tmp >%APPNAME%.jad

