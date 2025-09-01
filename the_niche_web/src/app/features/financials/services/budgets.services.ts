import {Injectable} from "@angular/core";
import {Budget} from "../finance.model";
import {BehaviorSubject, Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class BudgetsService {

    getAllBudgets():Observable<Budget[]> {
        return new BehaviorSubject<Budget[]>(budgets);
    }
}



const budgets : Budget[]=[
    {
        "id": "b1a92d3f-8b7d-4f3e-9d4e-1c9a39e56f77",
        "name": "Groceries",
        "limit": 1000,
        "spent": 890,
        "remaining": 110,
        "period": "monthly",
        "startDate": "2025-09-01",
        "endDate": "2025-09-30",
        "transactions": [
            {
                "id": "t-101",
                "type": "expense",
                "amount": 200,
                "description": "Supermarket shopping",
                "date": "2025-09-03",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-102",
                "type": "expense",
                "amount": 150,
                "description": "Butcher & Fish Market",
                "date": "2025-09-08",
                "paymentMethod": "Cash"
            },
            {
                "id": "t-103",
                "type": "expense",
                "amount": 300,
                "description": "Monthly bulk groceries",
                "date": "2025-09-15",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-104",
                "type": "expense",
                "amount": 120,
                "description": "Vegetables & fruits",
                "date": "2025-09-18",
                "paymentMethod": "Cash"
            },
            {
                "id": "t-105",
                "type": "expense",
                "amount": 120,
                "description": "Snacks & drinks",
                "date": "2025-09-25",
                "paymentMethod": "Debit Card"
            }
        ]
    },
    {
        "id": "b2a18e2f-9c1b-42ab-8f8d-3a2c87d50f11",
        "name": "Transport",
        "limit": 600,
        "spent": 480,
        "remaining": 120,
        "period": "monthly",
        "startDate": "2025-09-01",
        "endDate": "2025-09-30",
        "transactions": [
            {
                "id": "t-201",
                "type": "expense",
                "amount": 150,
                "description": "Fuel top-up",
                "date": "2025-09-02",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-202",
                "type": "expense",
                "amount": 50,
                "description": "Grab ride",
                "date": "2025-09-05",
                "paymentMethod": "E-Wallet"
            },
            {
                "id": "t-203",
                "type": "expense",
                "amount": 120,
                "description": "Parking fees",
                "date": "2025-09-10",
                "paymentMethod": "Cash"
            },
            {
                "id": "t-204",
                "type": "expense",
                "amount": 80,
                "description": "Fuel top-up",
                "date": "2025-09-17",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-205",
                "type": "expense",
                "amount": 80,
                "description": "Grab ride",
                "date": "2025-09-23",
                "paymentMethod": "E-Wallet"
            }
        ]
    },
    {
        "id": "b3b92d3f-7c9d-42f2-9a6e-4d8f91e77a55",
        "name": "Entertainment",
        "limit": 800,
        "spent": 770,
        "remaining": 30,
        "period": "monthly",
        "startDate": "2025-09-01",
        "endDate": "2025-09-30",
        "transactions": [
            {
                "id": "t-301",
                "type": "expense",
                "amount": 200,
                "description": "Concert tickets",
                "date": "2025-09-04",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-302",
                "type": "expense",
                "amount": 150,
                "description": "Streaming subscription",
                "date": "2025-09-07",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-303",
                "type": "expense",
                "amount": 120,
                "description": "Cinema tickets",
                "date": "2025-09-12",
                "paymentMethod": "Cash"
            },
            {
                "id": "t-304",
                "type": "expense",
                "amount": 200,
                "description": "Weekend trip",
                "date": "2025-09-19",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-305",
                "type": "expense",
                "amount": 100,
                "description": "Bowling & arcade",
                "date": "2025-09-26",
                "paymentMethod": "Cash"
            }
        ]
    },
    {
        "id": "b4f71a3e-6e2d-45cf-b97e-6e9a42c15f22",
        "name": "Utilities",
        "limit": 1200,
        "spent": 1120,
        "remaining": 80,
        "period": "monthly",
        "startDate": "2025-09-01",
        "endDate": "2025-09-30",
        "transactions": [
            {
                "id": "t-401",
                "type": "expense",
                "amount": 300,
                "description": "Electricity bill",
                "date": "2025-09-06",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-402",
                "type": "expense",
                "amount": 250,
                "description": "Water bill",
                "date": "2025-09-09",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-403",
                "type": "expense",
                "amount": 200,
                "description": "Internet & TV",
                "date": "2025-09-13",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-404",
                "type": "expense",
                "amount": 180,
                "description": "Mobile plans",
                "date": "2025-09-20",
                "paymentMethod": "E-Wallet"
            },
            {
                "id": "t-405",
                "type": "expense",
                "amount": 190,
                "description": "Gas bill",
                "date": "2025-09-27",
                "paymentMethod": "Cash"
            }
        ]
    },
    {
        "id": "b5c84d9a-5d6f-41e2-9f66-5f9a92b16f33",
        "name": "Dining Out",
        "limit": 700,
        "spent": 640,
        "remaining": 60,
        "period": "monthly",
        "startDate": "2025-09-01",
        "endDate": "2025-09-30",
        "transactions": [
            {
                "id": "t-501",
                "type": "expense",
                "amount": 120,
                "description": "Family dinner",
                "date": "2025-09-01",
                "paymentMethod": "Credit Card"
            },
            {
                "id": "t-502",
                "type": "expense",
                "amount": 80,
                "description": "Lunch with colleagues",
                "date": "2025-09-06",
                "paymentMethod": "Cash"
            },
            {
                "id": "t-503",
                "type": "expense",
                "amount": 150,
                "description": "Weekend restaurant",
                "date": "2025-09-11",
                "paymentMethod": "Debit Card"
            },
            {
                "id": "t-504",
                "type": "expense",
                "amount": 130,
                "description": "Takeaway order",
                "date": "2025-09-16",
                "paymentMethod": "E-Wallet"
            },
            {
                "id": "t-505",
                "type": "expense",
                "amount": 160,
                "description": "Dinner with friends",
                "date": "2025-09-22",
                "paymentMethod": "Credit Card"
            }
        ]
    }
]
