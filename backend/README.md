ZIDIO Connect Backend

Quick start

- Requires Java 17 and Maven (or use Docker below)
- Run: mvn spring-boot:run

API

- POST /api/auth/register { email, password, fullName, role }
- POST /api/auth/login { email, password }
- GET /api/jobs
- POST /api/jobs (RECRUITER or ADMIN)
- POST /api/applications/apply/{jobId} (STUDENT)
- GET /api/applications/me (STUDENT)
- POST /api/applications/{id}/status/{status} (RECRUITER/ADMIN)
- GET/POST /api/profile (STUDENT)
- POST /api/excel/analyze (multipart file)

Profiles

- Default: in-memory H2
- MySQL: set SPRING_PROFILES_ACTIVE=mysql and provide DB env vars

ZIDIO Connect Backend

Quick start

- Java 17, Maven
- Run: mvn spring-boot:run

API

- POST /api/auth/register { email, password, fullName, role }
- POST /api/auth/login { email, password }
- GET /api/jobs
- POST /api/jobs (RECRUITER or ADMIN)

Default DB: in-memory H2. Switch to MySQL by setting spring.datasource properties.

