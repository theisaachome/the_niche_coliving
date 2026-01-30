import {Component, Input, OnInit} from "@angular/core";
import {RoomAssignmentService} from "../room-assignment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RoomAssignmentOverviewResponse} from "../room-assignment.model";


@Component({
    selector: "app-assignments",
    template:`
        @if (roomAssignments.length ){
            <h2>Assignments</h2>
            <table class="ui single line table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Deposit Amount</th>
                </tr>
                </thead>
                <tbody>
                    @for (assignment of roomAssignments; track assignment.id) {
                        <tr>
                            <td>{{assignment.id}}</td>
                            <td>{{assignment.tenantName}}</td>
                            <td>{{assignment.assignmentStatus}}</td>
                            <td>{{assignment.startDate}}</td>
                            <td>{{assignment.endDate}}</td>
                            <td>{{assignment.depositAmount}}</td>
                        </tr>
                    }
                </tbody>
            </table>
        } @else {
            <h2 class="ui title">The Room is empty Still available for new assignment</h2>
        }
    `,
    styles:``
})
export class AssignmentsComponent implements OnInit {

    roomAssignments!:RoomAssignmentOverviewResponse[];

    roomId!: string;
    constructor(private roomAssignmentService: RoomAssignmentService,
                private route:ActivatedRoute,
                private router: Router) {
    }
    ngOnInit(): void {
        this.route.paramMap.subscribe(paramMap => {
            this.roomId = paramMap.get("roomId")!;
            this.loadRoomAssignment(this.roomId);
        })
        this.loadRoomAssignment(this.roomId);
    }

    loadRoomAssignment(roomId:string){
        this.roomAssignmentService
            .getRoomAssignmentByRoomId(roomId)
            .subscribe((res)=>{
                this.roomAssignments = res
                console.log(res)
            },error => {
                console.log(error);
            },()=>{});
    }

}
