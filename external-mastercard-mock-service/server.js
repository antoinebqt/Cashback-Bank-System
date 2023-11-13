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

    let mastercardTransactionId = generateRandomNumericString(10);

    // call external store mock
    if (transaction.merchant_mid === 'MID123') {
        axios.post('http://localhost:5003/api/store/purchaseMade', mastercardTransactionId).then(r => console.log(r));
        // axios.post('http://localhost:5003/api/store/return', mastercardTransactionId).then(r => console.log(r));
    } else if (transaction.merchant_mid === 'MID789') {
        // axios.post('http://localhost:5004/api/store/purchaseMade', mastercardTransactionId).then(r => console.log(r));
        axios.post('http://localhost:5004/api/store/return', mastercardTransactionId).then(r => console.log(r));
    }

    console.log('Transaction validated : ' + transaction.toString());
    res.send(mastercardTransactionId);
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
