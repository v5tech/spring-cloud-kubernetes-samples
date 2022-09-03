# manifests

```bash
kubectl --namespace default patch svc spring-cloud-kubernetes-configserver -p '{"spec": {"type": "NodePort"}}'
kubectl --namespace default patch svc spring-cloud-kubernetes-configuration-watcher -p '{"spec": {"type": "NodePort"}}'
kubectl --namespace default patch svc spring-cloud-kubernetes-discoveryserver -p '{"spec": {"type": "NodePort"}}'
```