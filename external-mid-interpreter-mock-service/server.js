const express = require('express');
const app = express();
const port = 8080;

app.use(express.json());

// Mapping between SIRET and MID
const siretToMidMapping = {
    '12345678900010': ['MID123', 'MID456'],
    '98765432100020': ['MID789', 'MID012'],
    '55555555500030': ['MID345', 'MID678'],
    '00000000000001': ['MID111'],
    '00000000000002': ['MID222'],
    '00000000000003': ['MID333'],
    '00000000000004': ['MID444'],
    '00000000000005': ['MID555'],
    '00000000000006': ['MID666'],
    '00000000000007': ['MID777'],
    '00000000000008': ['MID888'],
    '00000000000009': ['MID999'],
};

function getSiretFromMid(merchant_mid) {
    const siret = Object.keys(siretToMidMapping).find(siret => siretToMidMapping[siret].includes(merchant_mid));
    return siret;
}

// Endpoint GET do get the siret number relate to the merchant mid
app.get('/api/siret/:mid', (req, res) => {
    const merchant_mid = req.params.mid;
    console.log(`Request received to get siret related to the merchant MID : ${merchant_mid}`);
    if (!merchant_mid) {
        return res.status(400).json({ error: 'Params merchant mid empty' });
    }

    const siret = getSiretFromMid(merchant_mid);
    if (!siret) {
        return res.status(404).json({ error: 'Siret not found' });
    }

    res.send(siret);
});

// Start the server
app.listen(port, () => {
    console.log(`Server started on http://localhost:${port}`);
});
