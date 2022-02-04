import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../services/user';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  users!: User[];
  editUser!: User;
  deleteUser!: User;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(this.users);
      },
      (error: HttpErrorResponse) => {
        this.displayErrMsg(error);
      }
    );
  }

  onAddUser(addForm: NgForm): void {
    document.getElementById('add-user-form')!.click();
    this.userService.addUser(addForm.value).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        this.displayErrMsg(error);
        addForm.reset();
      }
    );
  }

  onUpdateUser(user: User): void {
    this.userService.updateUser(user).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        this.displayErrMsg(error);
      }
    );
  }

  onDeleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        this.displayErrMsg(error);
      }
    );
  }

  searchUsers(key: string): void {
    const results: User[] = [];
    (async () => {
      this.users = await this.userService.getUsers().toPromise();
      for (const user of this.users) {
        if (user.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
          results.push(user);
        }
      }
      this.users = results;
    })();
  }

  // public searchUsers(key: string): void {
  //   this.userService.serachUsers(key.trim()).subscribe(
  //     (response: User[]) => {
  //   	console.log(key);
  //       this.users = response;
  //     },
  //     (error: HttpErrorResponse) => {
  //       this.displayErrMsg(error);
  //     }
  //   );
  // }

  openAddModal(): void {
    this.initOpenModal("add");
  }

  openEditModal(user: User): void {
    this.editUser = user;
    this.initOpenModal("update");
  }

  openDeleteModal(user: User): void {
    this.deleteUser = user;
    this.initOpenModal("delete");
  }

  private initOpenModal(modalName: String): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-target', '#'+modalName+'UserModal');
    button.setAttribute('data-toggle', 'modal');
    container?.appendChild(button);
    button.click();
  }

  private displayErrMsg(httpError: HttpErrorResponse): void {
    let errorMsg: string = httpError.message;
    if (httpError.error.message != undefined) {
      errorMsg = httpError.error.message;
    }
    console.log(httpError);
    Swal.fire({
      title: 'Error!',
      html: errorMsg,
      icon: 'error',
      confirmButtonText: 'OK',
    });
  }
}
