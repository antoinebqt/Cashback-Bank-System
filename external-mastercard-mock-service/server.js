const express = require('express');
const axios = require('axios');

const app = express();
const port = 8080;

app.use(express.json());

function generateRandomNumericString(length) {
    let result = '';
    const characters = '0123456789';

    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        result += characters.charAt(randomIndex);
    }

    return result;
}


// Endpoint POST to validate a payment
app.post('/api/transaction-payment', (req, res) => {
    console.log('Request received to pay a transaction');

    const transactionData = req.body;
    const transaction = {
        merchant_mid: transactionData.mid,
        amount: transactionData.amount
    }

    if (!transaction.amount || !transaction.merchant_mid) {
        console.log('Missing body data : ' + transaction.amount + ' ' + transaction.merchant_mid);
        return res.status(400).send(false);
    }

    let id = generateRandomNumericString(10);

    // call external store mock
    if (transaction.merchant_mid === 'MID123') {
        axios.post('http://external-carrefour-mock-service:8080/api/store/return', id, {headers: {"Content-Type": "text/plain"}})
            .then((response) => console.log(response.data));
    } else if (transaction.merchant_mid === 'MID789') {
        axios.post('http://external-decathlon-mock-service:8080/api/store/return', id, {headers: {"Content-Type": "text/plain"}})
            .then((response) => console.log(response.data));
    }

    console.log('Transaction validated : ' + transaction.toString());
    res.send(id);
});

// Start the server
app.listen(port, () => {
    console.log(`Server started on http://localhost:${port}`);
});

// function POST(url, body) {
//     const requestOptions = {
//         method: 'POST',
//         headers: {'Content-Type': 'application/json'},
//         body: body
//     };
//     axios.post()
//     return fetch(url, requestOptions);
// }
