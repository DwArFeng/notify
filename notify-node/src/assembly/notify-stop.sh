#!/bin/sh
# 程序的根目录
basedir=/usr/local/notify

PID=$(cat "$basedir/notify.pid")
kill "$PID"
