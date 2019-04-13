#!/bin/sh

JAVA_OPTS="-Xms256m -Xmx512m -Xmn256m"


echo "Starting cash message"

mkdir -p  /data/logs/message/
rm -f /data/logs/message/startup.log
touch /data/logs/message/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-message.jar > /data/logs/message/startup.log &
PID="$!"
echo ${PID}


for i in `seq 1 100`
do
        grep 'Started MessageApplication' /data/logs/message/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash Message is started up."
                break
        fi
        sleep 3
done

grep 'Started MessageApplication' /data/logs/message/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash Message is failed"
    exit 1
fi

sleep 10
echo "Starting cash activity"
mkdir -p  /data/logs/activity/
rm -f /data/logs/activity/startup.log
touch /data/logs/activity/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-activity.jar > /data/logs/activity/startup.log &

for i in `seq 1 100`
do
        grep 'Started ActivityApplication' /data/logs/activity/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash activity is started up."
                break
        fi
        sleep 3
done

grep 'Started ActivityApplication' /data/logs/activity/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash activity is failed"
    exit 1
fi

sleep 10
echo "Starting cash product"
mkdir -p  /data/logs/product/
rm -f /data/logs/product/startup.log
touch /data/logs/product/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-product.jar > /data/logs/product/startup.log &

for i in `seq 1 100`
do
        grep 'Started ProductApplication' /data/logs/product/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash product is started up."
                break
        fi
        sleep 3
done

grep 'Started ProductApplication' /data/logs/product/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash product is failed"
    exit 1
fi

sleep 10
echo "Starting cash user"

mkdir -p  /data/logs/user/
rm -f /data/logs/user/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-user.jar > /data/logs/user/startup.log &
PID="$!"
echo ${PID}


for i in `seq 1 100`
do
        grep 'Started UserApplication' /data/logs/user/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash User is started up."
                break
        fi
        sleep 3
done

grep 'Started UserApplication' /data/logs/user/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash User is failed"
    exit 1
fi

sleep 10
echo "Starting cash risk"
mkdir -p  /data/logs/risk/
rm -f /data/logs/risk/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-risk.jar > /data/logs/risk/startup.log &

for i in `seq 1 100`
do
        grep 'Started RiskApplication' /data/logs/risk/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash risk is started up."
                break
        fi
        sleep 3
done

grep 'Started RiskApplication' /data/logs/risk/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash risk is failed"
    exit 1
fi

sleep 10
echo "Starting cash trade"
mkdir -p  /data/logs/trade/
rm -f /data/logs/trade/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-trade.jar > /data/logs/trade/startup.log &

for i in `seq 1 100`
do
        grep 'Started TradeApplication' /data/logs/trade/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash trade is started up."
                break
        fi
        sleep 3
done

grep 'Started TradeApplication' /data/logs/trade/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash trade is failed"
    exit 1
fi

sleep 10
echo "Starting cash collection"
mkdir -p  /data/logs/collection/
rm -f /data/logs/collection/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-collection.jar > /data/logs/collection/startup.log &

for i in `seq 1 100`
do
        grep 'Started CollectionApplication' /data/logs/collection/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash collection is started up."
                break
        fi
        sleep 3
done

grep 'Started CollectionApplication' /data/logs/collection/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash collection is failed"
    exit 1
fi

sleep 10
echo "Starting cash task"
mkdir -p  /data/logs/task/
rm -f /data/logs/task/startup.log
touch /data/logs/task/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-task.jar > /data/logs/task/startup.log &

for i in `seq 1 100`
do
        grep 'Started TaskApplication' /data/logs/task/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash task is started up."
                break
        fi
        sleep 3
done

grep 'Started TaskApplication' /data/logs/task/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash task is failed"
    exit 1
fi

sleep 10
echo "Starting cash front"
mkdir -p  /data/logs/front/
rm -f /data/logs/front/startup.log
touch /data/logs/front/startup.log
nohup java ${JAVA_OPTS} -jar -Dspring.profiles.active=dev  /opt/cashbus/apps/cash-front.jar > /data/logs/front/startup.log &


for i in `seq 1 100`
do
        grep 'Started FrontApplication' /data/logs/front/startup.log
        if [[ $? -eq 0 ]]; then
                echo "cash front is started up."
                break
        fi
        sleep 3
done

grep 'Started FrontApplication' /data/logs/front/startup.log
if [[ $? -ne 0 ]]; then
    echo "cash front is failed"
    exit 1
fi

