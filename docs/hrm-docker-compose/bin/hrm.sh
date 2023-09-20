echo '=====开始运行mysql====='
docker-compose -f ../ymal/mysql.yml up -d

echo '=====开始运行hrm====='
docker-compose -f ../ymal/hrm.yml up -d

echo '=====开始运行vue-hrm====='
docker-compose -f ../ymal/vue_hrm.yml up -d
