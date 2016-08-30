FROM jetty:9
VOLUME /webapps
ADD build/libs/REALSuiteArch-1.0.jar REALSuiteArch-1.0.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","REALSuiteArch-1.0.jar"]
