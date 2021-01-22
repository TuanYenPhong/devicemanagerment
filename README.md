<<<<<<< .mine
# Deploy spring boot and angular to tomcat serever (ubuntu server)
=======

>>>>>>> .theirs


## Angular
#### Build file dist: ng build --prod
#### Config tomcat
- tomcat / config / server.xml
    - add <Host><Valve className="org.apache.catalina.valves.rewrite.RewriteValve" /> </Host>
- tomcat / config
    - create folder Catalina/localhost/rewrite.config
    - rewrite.config add:
        * RewriteCond %{REQUEST_PATH} !-f
        * RewriteRule ^/device-ui/(.*) /device-ui/index.html
        * (device-ui into webapps, index.html into device-ui)
## Install tomcat
- sudo apt update
- sudo apt install openjdk-8-jdk(cài đặt jdk theo spring boot)
- Tạo tomcat user - tạo groupname: sudo groupadd tomcat
- Tạo user: sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat
- Cài đặt tomcat: cd /tmp
- dowload tomcat 8: curl -O https://mirror.downloadvn.com/apache/tomcat/tomcat-8/v8.5.61/bin/apache-tomcat-8.5.61.tar.gz
- Tạo folder và giải nén: 
- sudo mkdir /opt/tomcat 
sudo tar xzvf apache-tomcat-8.5.61.tar.gz -C /opt/tomcat --strip-components=1
- Phân quyền tomcat(ubuntu)
sudo chmod -R 777 /opt/tomcat/logs(webapps)
cd /opt/tomcat
sudo chmod -R g+r conf
sudo chmod g+x conf
sudo chown -R tomcat webapps/ work/ temp/ logs/
- Tạo tomcat server
Xác định vị trí của JDK để gán cho biến JAVA_HOME: sudo update-java-alternatives -l
- tạo file tomcat.service: sudo nano /etc/systemd/system/tomcat.service
Nhập nội dung file:
[Unit]
Description=Apache Tomcat Web Application Container
After=network.target
[Service]
Type=forking
Environment=JAVA_HOME=usr/lib/jvm/java-1.11.0-openjdk-amd64
Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat
Environment=CATALINA_BASE=/opt/tomcat
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'
ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh
User=tomcat
Group=tomcat
UMask=0007
RestartSec=10
Restart=always
[Install]
WantedBy=multi-user.target
(java home = đường dẫn file jdk ở câu lệnh trước)
- sudo systemctl daemon-reload ( reload)
- sudo systemctl start tomcat (chạy tomcat)
- sudo systemctl status tomcat (status tomcat)
- sudo ufw allow 8080 (cho phép chạy 8080 - http://server_domain_or_IP:8080)
- sudo nano /opt/tomcat/conf/tomcat-users.xml (add user web quản lý tomcat)
thêm user vào tomcat-users 
tomcat-users . . .>
    <user username="admin" password="password" roles="manager-gui,admin-gui"/>
</tomcat-users>
- sudo nano /opt/tomcat/webapps/manager/META-INF/context.xml 
- sudo nano /opt/tomcat/webapps/host-manager/META-INF/context.xml
comment tag limit IP như sau:
<Context antiResourceLocking="false" privileged="true" >
  <!--<Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />-->
</Context>
- sudo systemctl restart tomcat (restart tomcat)
- Copy file ware hoặc folder dist(angular) vào webapps để chạy

## Spring boot
- Add SpringBootServletInitializer
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
<packaging>war</packaging>
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
</dependency>
- Chú ý kiểm tra pass/user mysql (ubuntu or window)

## Thêm user vào mysql (ubuntu)
- SET GLOBAL validate_password_policy=LOW;
- CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
- GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost'; (quyền truy cập)
- SHOW GRANTS FOR 'username'@'localhost';
- GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'newuser'@'localhost' WITH GRANT OPTION;
