build-NzuguemGithubBotFunctionZip:
	@./mvnw package -DskipTests
	@cp src/main/resources/run.sh $(ARTIFACTS_DIR)
	@cp -R target/quarkus-app/* $(ARTIFACTS_DIR)