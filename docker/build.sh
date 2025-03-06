docker compose up --build -d
echo 'restarting container..please wait.. '
sleep 15
docker container restart be-wego
echo 'service backend ready.. '

echo 'restoring car park data..please wait'
docker container restart db-wego
sleep 15
docker exec -i db-wego /usr/bin/mysql -u root --password=PassWego2025! carpark < /root/apps/wego/sql/initial/car_parks.sql 
echo 'restoring car park data done..'