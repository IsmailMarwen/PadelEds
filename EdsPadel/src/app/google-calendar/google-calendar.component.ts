import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Calendar } from '@bryntum/calendar';

@Component({
  selector: 'app-google-calendar',
  templateUrl: './google-calendar.component.html',
  styleUrls: ['./google-calendar.component.css']
})
export class GoogleCalendarComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.initializeCalendar();
  }

  initializeCalendar(): void {
    const calendar = new Calendar({
      appendTo: 'calendar-container',
      date: new Date(),
      crudManager: {
        autoLoad: true,
        transport: {
          load: {
            url: 'assets/data/events.json' 
          },
          sync: {
            url: 'assets/data/sync.json' 
          }
        }
      }
    });
  }
}
