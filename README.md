# learning-angular-and-springboot
This is a repository for learning purposes.   
学習目的のリポジトリです。  
    
## Overview  
I made it by studying Angular and Spring Boot.  
Functions are "Login / Logout", "Data Manipulation", and "Tic-tac-toe".  
There are two types of account roles: "administrator" and "general".  
Security is only validated. Please note.  
    
AngularとSpringBootの勉強で作りました。  
機能としては「ログイン/ログアウト」、「データ操作」、「三目並べ」です。  
アカウントのロールは「管理者」と「一般」の二種類あります。  
セキュリティはバリデーションしか行っていません。ご留意下さい。  
    
## Demo  
admin(管理)  
![admin_role](https://user-images.githubusercontent.com/39521946/152396258-00b3c69b-f54a-477c-9ed4-adf349815ff1.gif)  
    
general(一般)
![user_role](https://user-images.githubusercontent.com/39521946/152396516-fcecf55b-85bf-43e1-abb9-2a4f7005949b.gif)

## Usage  
1. Open the project with vscode  
2. Follow the instructions of vscode to start the container with Remote Containers * It takes time to start  
3. Run "mvn spring-boot: run" in the api container  
4. Go to the "front-app" directory of the web container and run "ng serve --host 0.0.0.0"  
5. Access "http: // localhost: 4200 /" with a web browser  
6. You can log in with "root / password" for the administrator and "test1 / password" for the general public.  
    
1.vscodeでプロジェクトを開く  
2.vscodeの案内に従い、Remote Containersでコンテナを起動させる※起動には時間がかかります  
3.apiコンテナで「mvn spring-boot:run」を実行する  
4.webコンテナの「front-app」ディレクトリに移動し、「ng serve --host 0.0.0.0」を実行する  
5.webブラウザで「http://localhost:4200/」にアクセスする  
6.ログインは管理者が「root/password」、一般が「test1/passward」で出来ます。  
    

## Description  
We will describe the points to keep in mind for various functions.  
・Login: Implemented by JWT. Tokens are not a secure implementation. be careful.  
・User list: The initial user is listed in the API src / main / resources / import.sql.  
・Add / Delete / Edit User: Only simple validation is implemented. The reflected content will switch automatically.  
・ Tic-tac-toe: I'm writing on Angular. In particular, it does not communicate with the Spring Boot side.  
  
各種機能の留意点などを記載していきます。  
・ログイン : JWTで実装しています。トークンはセキュアな実装になっていません。ご注意下さい。  
・ユーザ一覧 : 初期ユーザは、APIのsrc/main/resources/import.sqlに記載されています。  
・ユーザ追加・削除・編集 : 簡単なバリデーションのみ実装しています。反映内容は自動で切り替わります。  
・三目並べ：Angular上で書いています。特にSpringBoot側と通信はしません。  
  

If you have any questions, please ask! I will answer as much as possible! !!  
Thank you for visiting this repository  
  
分からないことがあれば質問して下さい！可能な範囲で答えます！！  
このリポジトリを見てくれてありがとうございました　  

## Licence
MIT
