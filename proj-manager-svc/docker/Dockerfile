FROM openjdk:8
ADD /proj-manager-svc-0.0.1-SNAPSHOT.jar proj-manager-svc-0.0.1-SNAPSHOT.jar
EXPOSE 8092
RUN bash -c 'touch /proj-manager-svc-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","proj-manager-svc-0.0.1-SNAPSHOT.jar"]
