import http from 'k6/http';
import { sleep, check } from 'k6';

function createUser(id) {
    let url = 'http://localhost:3002/api/account/register';
    let payload = {
        firstName: `firstName_${id}`,
        lastName: `lastName_${id}`,
        email: `${id}@mail.com`,
        password: `password_${id}`
    };

    let headers = { 'Content-Type': 'application/json' };

    let response = http.post(url, JSON.stringify(payload), { headers: headers });
    check(response, {
        'User created successfully': (r) => r.status === 201,
    });

    return response.json(); // Return the created user data
}

function addMoneyToAccount(iban, amount) {
    let url = `http://localhost:5000/create-bank-transfer`;
    let payload = {
        iban: iban,
        amount: amount,
    };

    let headers = { 'Content-Type': 'application/json' };

    let response = http.post(url, JSON.stringify(payload), { headers: headers });
    check(response, {
        'Money added successfully': (r) => r.status === 200,
    });
}

export default function () {
    let userNumber = 1000;
    let usersIban = [];
    for (let i = 0; i < userNumber; i++) {
        let user = createUser(i);
        usersIban.push(user.bankAccount.iban);
    }
    sleep(1);
    for (let i = 0; i < usersIban.length; i++) {
        addMoneyToAccount(usersIban[i], 1000000);
    }
}
