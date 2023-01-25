# Polar Bookshop

![](./polarbookshop.png)
---
## Set Up Instructions
### 1. Install required software
* Install [Docker](https://external.ink?to=https://www.docker.com/) 
* Install [Docker Compose](https://external.ink?to=https://docs.docker.com/compose/install/)
* Install [Minikube](https://external.ink?to=https://minikube.sigs.k8s.io/docs/start/) 
* Install [Kubectl](https://external.ink?to=https://kubernetes.io/docs/tasks/tools/)
* Install [Tilt](https://external.ink?to=https://docs.tilt.dev/index.html)

### 2. Clone this Repository
Clone this repository and run the following commands in the root directory
of the repository

### 3. Run with Docker Compose
```bash
docker compose up
```

### 4. Develop with Tilt in Minikube

```bash
chmod +x ./create-k8s-cluster
./create-k8s-cluster
tilt up
```
