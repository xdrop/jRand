#!/bin/zsh

mvn versions:set -DnewVersion=$1
version=$(cat VERSION)
find . -iregex '.*\(README\|_coverpage\).md' -exec sed -i -e s/$version/$1/g {} \;
sed -i -E -e "s/documentation-(.+)-green.svg/documentation-$(echo $1 | sed 's/-/--/g')-green.svg/g" README.md
echo $1 > VERSION
