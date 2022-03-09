#!/bin/sh

remote_host="$1"
boot_port="$2"
remote_user="root"
remote_port=22
ssh_key=~/.ssh/id_rsa_cellx_base_temp

## 生成key
ssh-keygen -m PEM -t rsa -N '' -f ${ssh_key}

## 登录服务器
ssh-copy-id -i ${ssh_key} ${remote_user}@"${remote_host}"

## firewall
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "setsebool -P httpd_can_network_connect 1"
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "firewall-cmd --add-port=${boot_port}/udp --zone=public --permanent"
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "firewall-cmd --reload"
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "semanage port -a -t http_port_t -p udp ${boot_port}"

## upload jar
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "mkdir /root/jars"
scp -P ${remote_port} -i ${ssh_key} ../released/UDPServer.jar ${remote_user}@"${remote_host}":/root/jars/UDPServer.jar

## upload config
scp -P ${remote_port} -i ${ssh_key} ../config/UDPServer.service ${remote_user}@"${remote_host}":/usr/lib/systemd/system/

## restart service
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "systemctl daemon-reload"
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "systemctl enable UDPServer"
ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "systemctl restart UDPServer"

## reboot server
#ssh -p ${remote_port} -i ${ssh_key} ${remote_user}@"${remote_host}" "reboot"

## remove ssh_key
rm -f ${ssh_key}
rm -f ${ssh_key}.pub
