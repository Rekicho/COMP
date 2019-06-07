# COMP

Method overloading not supported.

## Optimizations

### -r

Not done yet.

### -o

Constant propagation: only on variables = CONSTANT that have only 1 definition.

While template included in not optimized version.

## Compile

``` javac -d bin ast/*.java jmm/*.java ```

## Execute

### Using jar

``` java -jar jmm.jar [-o] examples/<input_file.jmm> ```

``` java -jar jasmin.jar <input_file.j> ```

``` java <ClassName> ```

### Not using jar

``` cd bin ```

``` java jmm/jmm [-o] ../examples/<input_file.jmm> ```

``` java -jar ../jasmin.jar <input_file.j> ```

``` java <ClassName> ```