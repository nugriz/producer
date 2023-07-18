# Producer Service
Service that used as producer to produce kafka broker and topic

# Docker Image
rizkinu/intern:1.4

# Environment Variable
ENV_SERVER_PORT:8080
ENV_KAFKA_BROKERS_URL:http://beam.tritronik.com:29092
ENV_TOPIC_NAME:INTERN_TEST_EVENT_TOPIC
ENV_SERVER_EUREKA:http://discovery-service:8761/eureka
