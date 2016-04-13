rm ./RUNNING_PID 2> /dev/null
java -Xms512M -Xmx1536M -Xss8M -XX:+CMSClassUnloadingEnabled -XX:ReservedCodeCacheSize=128M -XX:+UseConcMarkSweepGC -Dhttp.port=8000 -Dfile.encoding=UTF-8 -jar `dirname $0`/sbt-launch.jar "$@"

