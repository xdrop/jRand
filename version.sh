#!/bin/bash

mvn versions:set -DnewVersion=$1
version=$(cat VERSION)
find -E . -iregex '.*(README|_coverpage).md' -exec sed -i .bak -e s/$version/$1/g {} \;
sed -E -i .bak -e s/documentation-(.+)-green.svg/documentation-$(cat version | sed 's/-/--/g)-green.svg/g README.md
