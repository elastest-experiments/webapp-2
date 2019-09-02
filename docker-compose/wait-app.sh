PORT=$1
while ! timeout 1 bash -c "echo > /dev/tcp/localhost/$PORT"; do
   echo "Waiting app in port $PORT"
   sleep 2
done