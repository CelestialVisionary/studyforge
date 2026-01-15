# 第一阶段：构建阶段
FROM maven:3.8.6-openjdk-17 AS build

# 设置工作目录
WORKDIR /app

# 复制pom.xml文件
COPY pom.xml .

# 下载依赖
RUN mvn dependency:go-offline -B

# 复制源代码
COPY src ./src

# 构建应用
RUN mvn package -DskipTests

# 第二阶段：运行阶段
FROM openjdk:17-jdk-slim AS run

# 设置工作目录
WORKDIR /app

# 添加维护者信息
LABEL maintainer="studyforge-team <studyforge@example.com>"
LABEL version="1.0.0"
LABEL description="AI辅助学习工具 - StudyForge"

# 复制构建阶段的jar文件
COPY --from=build /app/target/exam-system-server-1.0.0.jar /app/app.jar

# 创建非root用户
RUN groupadd -r appuser && useradd -r -g appuser appuser

# 更改文件所有者
RUN chown -R appuser:appuser /app

# 切换到非root用户
USER appuser

# 暴露应用运行的端口
EXPOSE 8080

# 设置环境变量
ENV JAVA_OPTS="-Dspring.profiles.active=prod -Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# 添加健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# 运行应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
