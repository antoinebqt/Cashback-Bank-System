import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 1000 }, // Ramp up to 100 users in 1 minute
        { duration: '3m', target: 1000 }, // Stay at 100 users for 5 minutes
        // { duration: '1m', target: 0 },   // Ramp down to 0 users in 1 minute
    ],
};

export function setup() {
    let url = 'http://localhost:3002/api/account/all-users';

    // Customize the setup request as needed
    let setupResponse = http.get(url);
    check(setupResponse, {
        'Setup successful': (r) => r.status === 200,
    });

    return setupResponse.json();
}

export default function (setupData) {
    let numberOfUsers = 1000;
    let randomUserId = Math.floor(Math.random() * numberOfUsers);
    let user = setupData[randomUserId];

    let url = 'http://localhost:3000/api/transaction/pay';
    let payload = {
        cardNumber: user.bankAccount.card.cardNumber,
        cvv: user.bankAccount.card.cvv,
        expirationDate: user.bankAccount.card.expirationDate,
        amount: 10,
        mid: "MID345",
    };

    let headers = { 'Content-Type': 'application/json' };

    let response = http.post(url, JSON.stringify(payload), { headers: headers });
    check(response, {
        'Transaction successful': (r) => r.status === 200,
    });
}