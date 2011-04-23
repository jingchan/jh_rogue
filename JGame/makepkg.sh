
# ensure ./*~ are deleted
# ensure examples/*~ and examples/*/*~ are deleted
# ensure tutorial/*~ are deleted
# ensure any Thumbs.db are deleted
# ensure any .*.sw[po] are deleted

# don't forget to update version in author_message!

cd ..
tar zcvf jgame-$1.tar.gz JGame/README JGame/MANUAL JGame/LICENSE \
        JGame/CHANGES JGame/makepkg.sh JGame/doshrinkjar* \
		JGame/manifests JGame/make-* \
		JGame/*.jar JGame/*.jad JGame/*.jnlp \
		JGame/classes-midp \
		JGame/classes-jre \
		JGame/src-*/*/*.java JGame/src-*/*/*/*.java \
		JGame/src-*/*/*.html JGame/src-*/*/*/*.html \
        JGame/examples/*.java JGame/examples/*.tbl \
        JGame/examples/package.html \
        JGame/examples/gfx/ JGame/examples/sound/ \
        JGame/examples/nebulaalpha/* \
        JGame/examples/waterworld/* \
        JGame/examples/matrixminer/* \
        JGame/examples/packetstorm/* \
        JGame/examples/guardian/* \
        JGame/examples/ogrotron/* \
        JGame/examples/dingbats/* \
		JGame/tutorial \
		JGame/javadoc \
		JGame/gamegen/*.java JGame/gamegen/*.class JGame/gamegen/*.tbl \
		JGame/gamegen/README JGame/gamegen/examples/*.appconfig \
		JGame/gamegen/*.gif \
		JGame/gamegen/website/*.html JGame/gamegen/website/*.php \
        JGame/html/*.html JGame/html/*-desc.txt JGame/html/gen-jgame-html.pl \
        JGame/html/gen-html-*.sh \
        JGame/html/*.gif JGame/html/*.jpg \
		JGame/skeleton

