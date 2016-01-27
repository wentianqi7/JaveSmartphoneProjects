rm -rf database/ exception/ model/ util/ services/ *Servlet.java
s3cmd get s3://music.shake/backup/minisite.zip
unzip minisite.zip
rm minisite.zip
