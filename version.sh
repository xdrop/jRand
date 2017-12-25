#!/bin/bash

mvn versions:set -DnewVersion=$1
find . -name "README.md" -o -name "_coverpage.md" -exec sed -i .bak -e s/'[0-9]\.[0-9][0-9]*\.[0-9][0-9]*\(-[a-z][a-z]*\)\{0,1\}'/$1/g {} \;

