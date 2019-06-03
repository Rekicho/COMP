cd bin
java jmm/jmm ../examples/$1.jmm
java -jar ../jasmin.jar $1.j
java $1
cd ..