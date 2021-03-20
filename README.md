ณัฐดนัย คุ้มศิริ 6210450563 

* วิธีการติดตั้งโปรแกรม
  * แตกไฟล์ 6210450563.zip
    * เข้าไปในโฟรเดอร์ และ double click 6210450563.jar เพื่อเปิดโปรแกรม
  * ในกรณีที่ไม่สามารถเปิดโปรแกรมได้จากการ double click 6210450563.jar ให้เปิด command prompt หรือ terminal หรือ bash แล้วใช้คำสั่ง 'java -jar 6210450563.jar'
  
* src\main\java\taskTracking
  * Main.java
  
* src\main\java\taskTracking\controllers
  * CreateCategoryController.java
  * ForwardWorkController.java
  * GeneralWorkController.java
  * HomeController.java
  * ProfileController.java
  * ProjectWorkController.java
  * UpdateForwardWorksController.java
  * UpdateGeneralWorksController.java
  * UpdateProjectWorksController.java
  * UpdateWeeklyWorksController.java
  * WeeklyWorkController.java
  * WorksAllController.java
  * WorksCategoryHomeController.java
  
* src\main\java\taskTracking\model\WorksCategory
  * CategoryWork.java
  * ForwardWork.java
  * GeneralWork.java
  * ProjectWork.java
  * WeeklyWork.java
  * WorkNameList.java
  
* src\main\java\taskTracking\services
  * CategoryFileDataSource.java
  * DataList.java
  * DataSource
  * ForwardWorkFileDataSource.java
  * GeneralWorkFileDataSource.java
  * OpenPDFFile.java
  * ProjectWorkFileDataSource.java
  * StringConfiguration.java
  * WeeklyWorkFileDataSource.java
  
**Sprint 2** (2021-01-21)

* ทำให้ project เป็น maven project
* เปลี่ยน package sample เป็นชื่อที่เหมาะสม กับบริบทของโปรเจครายวิชา
* นำ FXRouter มาใช้ เพื่อช่วยในการเปลี่ยนหน้า
* ตั้งค่าไฟล์ pom.xml ให้พร้อม โดยใส่ plugin ให้ครบ และกำหนด properties ต่าง ๆ ที่สื่อความหมายถึงงานที่ทำ
* ทำหน้าแรกของโปรแกรม ใส่ชื่อโปรแกรม และปุ่มที่กดไปหาหน้าแสดงข้อมูลผู้จัดทำ
* ทำหน้าแสดงข้อมูลผู้จัดทำ ใส่รูป ชื่อ นามสกุล ชื่อเล่น รหัสนิสิต มีปุ่มย้อนกลับไปหน้าแรกได้

**Sprint 3** (2021-02-21)

* ออกแบบ model และมีการนำการอ่าน-เขียนไฟล์ ใน project แล้ว
* มีหน้า add ข้อมูลของ general works ลงในไฟล์ .csv 

**Sprint 3-2** (2021-02-22)

* แก้ไขการรับข้อมูลของ general works ใหม่
* เพิ่มหน้า update ของ general works และการทำงานใน controller

**Sprint 4** (2021-03-01)

* เพิ่ม model CategoryWork
    * มีหน้า add หมวดหมู่ เข้าไปใน general work
    * มีหน้า add หมวดหมู่ เข้าไปใน weekly work
    * มีหน้า add หมวดหมู่ เข้าไปใน forward work
  * มีหน้า add หมวดหมู่ เข้าไปใน project work
  * มีหน้าสร้าง หมวดหมู่
* เพิ่มหน้า How to Use หน้าเปล่า
* เพิ่มหน้า อ่านเขียนไฟล์ ของ weekly works
* เพิ่มหน้า อ่านเขียนไฟล์ ของ forward works
* เพิ่มหน้า อ่านเขียนไฟล์ ของ project works
* มีหน้า add ข้อมูลของ weekly works ลงในไฟล์ .csv 
* มีหน้า add ข้อมูลของ forward works ลงในไฟล์ .csv 
* มีหน้า add ข้อมูลของ project works ลงในไฟล์ .csv
* เพิ่มหน้า update ของ weekly works และการทำงานใน controller
* เพิ่มหน้า update ของ forward works และการทำงานใน controller
* เพิ่มหน้า update ของ project works และการทำงานใน controller
* เพิ่มหน้า Show งานทั้งหมด "WorksAll"

**Sprint 5** (2021-03-05)

* แก้ไขหน้า Input ของ general work, weekly work, forward work และ project work
* เพิ่มเงื่อนไข การ Input ค่าเข้าไปเก็บข้อมูล ของ general work, weekly work, forward work และ project work
* เพิ่มเงื่อนไข การ Input ค่าเข้าไปเก็บข้อมูล ของ Update general work, Update weekly work, Update forward work และ Update project work
* เพิ่ม extra ข้อ 15.4 ในหน้า add งาน general work, weekly work, forward work และ project work
* ตกแต่ง เพิ่ม background ในทุกหน้าที่มีข้อมูล

**Sprint 6** (2021-03-08)

* เพิ่มปุ่มไปหน้า WorksAll  ในหน้า  Update   งานทั้งหมด
* เพิ่มปุ่มไปหน้า Update งานต่างๆ ในหน้า WorksAll
* 14.4 (extra 5 คะแนน)เพิ่มระบบ filter เพื่อเลือกการแสดงผลรายการงานตามสถานะของงาน (ยังไม่เริ่ม, กำลังทำ, เสร็จสิ้น) ในหน้าของ workAll
* 14.3 (extra 5 คะแนน) มีระบบ filter เพื่อแสดงผลเฉพาะรายการงานในวันที่ระบุ ในหน้าของ workAll

**Sprint 7** (2021-03-15)

* เพิ่ม 18.4 (extra 5 คะแนน) GUI มี effect ที่น่าสนใจ เมื่อมี action ต่าง ๆ เช่น มีการ highlight button เมื่อคลิกปุ่ม มี effect การ transition เมื่อเปลี่ยน scene เป็นต้น
  * โดยใช้ css file ในการทำ
* ตกแต่งเพิ่มความสวยงามและไฮไลท์ ในหน้าต่างๆ 
* เพิ่ม 15.3 (extra 5 คะแนน) มีส่วนสำหรับการลากวาง (drag and drop) งานที่มีอยู่แล้วแต่ไม่ได้ระบุหมวดหมู่ ไปใส่ในหมวดหมู่
* เพิ่ม การค้นหา Category ใน filter ด้วย
* เพิ่ม TextArea ในหน้าเพื่อ show ชื่องานทั้งหมด ในหน้าสร้าง Category

**FinalProject** (2021-03-21)

* เพิ่มการ TableView show รายชื่องานทั้งหมดในหน้า CreateCategoryController และ Scene
* เพิ่ม WorkNameList ในการเก็บชื่อ งานทั้งหมดที่สร้าง
* แก้ไขเงื่อนต่างๆใน หน้า Update งานต่างๆใน Controller
* เพิ่ม method ในการเช็คชื่องานซ้ำ
* เพิ่ม field weeklyDate ในการรับ วันที่ประจำสัปดาที่เริ่มทำ
* เพิ่มการ show StatusLabel ในหน้า Update ทุกหน้า