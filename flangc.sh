java -jar out/artifacts/fp1_jar/fp1.jar $1 &
echo "***** Code was successfully generated *****"
echo "***** Starting compilation *****"
src_path=generated-src/compilation
kotlinc -nowarn $src_path/*.kt -include-runtime -d $src_path/program.jar
echo "***** Compilation has finished *****"