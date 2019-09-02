PORT=$1
while ! nc -z localhost $PORT; do
    echo "Waiting app in port $PORT"
    sleep 2
done