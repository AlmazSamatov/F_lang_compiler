java -jar out/artifacts/fp1_jar/fp1.jar $1 &
echo "***** Code was successfully generated *****"
echo "***** Starting execution *****"
src_path=generated-src/compilation
kotlinc -nowarn $src_path/gen_program.kt $src_path/SupportClasses.kt -include-runtime -d $src_path/program.jar &
java -jar $src_path/program.jar &
echo "***** Execution has completed *****"