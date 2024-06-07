#!/bin/sh

# 设置程序的根目录。
basedir="$(cd $(dirname $0)/.. && pwd)"

PID=$(cat "$basedir/notify.pid")
kill "$PID"
