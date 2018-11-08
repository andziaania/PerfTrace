import { Component, OnInit } from '@angular/core';
import { MetricsService } from '../metrics.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  currentUsersNumber: number;

  constructor(
    private metricsService: MetricsService
  ) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.metricsService.getCurrentUsersNumber()
        .subscribe(number => this.currentUsersNumber = number);
  }

}
