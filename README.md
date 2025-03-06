# JWT Springboot for Wego Test
This project for Wego test only.pleae install mySQL and restore the table first.
Make sure your IDE lombok support.

## Usage

```python
run project: mvn spring-boot:run
or 
java -jar target/test-iwan-0.0.1-SNAPSHOT.jar
```

## Endpoints
```python
GET /carparks/v1/nearest

curl --location 'https://wego-test-iwan.lihatkebunku.cloud/carparks/v1/nearest?latitude=1.37326&longitude=103.897&page=1&per_page=10'

Response:
[{"address":"BLK 464-468 HOUGANG AVENUE 10","latitude":1.3732591319490541,"longitude":103.89696590280224,"total_lots":433,"available_lots":254},{"address":"BLK 401-413, 460-463 HOUGANG AVENUE 10","latitude":1.3742926629562835,"longitude":103.89581761240308,"total_lots":705,"available_lots":177},{"address":"BLK 414-416 HOUGANG AVENUE 10","latitude":1.3754154819944029,"longitude":103.89650246570729,"total_lots":85,"available_lots":17},{"address":"BLK 351-357 HOUGANG AVENUE 7","latitude":1.3723422711738515,"longitude":103.89908052009055,"total_lots":232,"available_lots":61},{"address":"BLK 804 HOUGANG AVENUE 10","latitude":1.3712221378751506,"longitude":103.89475741107438,"total_lots":43,"available_lots":39},{"address":"BLK 364 / 365 UPPER SERANGOON RD","latitude":1.3701078117066328,"longitude":103.8972275612915,"total_lots":483,"available_lots":266},{"address":"BLK 417-434 HOUGANG AVENUE 6/8/10","latitude":1.3754251700704945,"longitude":103.89409369476076,"total_lots":837,"available_lots":291},{"address":"BLK 805 HOUGANG CENTRAL","latitude":1.3711840890820357,"longitude":103.89396218073213,"total_lots":341,"available_lots":304},{"address":"BLK 814 HOUGANG AVENUE 10","latitude":1.372683430169655,"longitude":103.89321220378666,"total_lots":89,"available_lots":88},{"address":"BLK 327-328,332-334,358-363 HOUGANG AVENUE 5","latitude":1.3691978333065977,"longitude":103.89642766021065,"total_lots":996,"available_lots":667}]

```
## Docker

```python

COPY target/test-iwan-0.0.1-SNAPSHOT.jar to docker directory
RUN ./build.sh

```

## Fetching Data

```python

The system will automatically fetch data from the endpoint https://api.data.gov.sg/v1/transport/carpark-availability every 3 minutes. If you want to disable automatic fetching, set the value of #carpark.api.canFetch# to false.

The fetching schedule can also be adjusted as needed by modifying the value of #carpark.cron#.

![fetching data](./fetching.png)

```
