const functions = require('firebase-functions');
const admin = require('firebase-admin');
const braintree = require('braintree');
const express = require('express');
const cors = require('cors');
const serviceAccount = require('./eat-res-halc-firebase-adminsdk-8v93p-f42b3b5e89.json');

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: 'https://eat-res-halc.firebaseio.com',
});

const app = express();

const authMiddleware = async (req, res, next) => {
    if (!req.headers.authorization || !req.headers.authorization.startsWith('Bearer')) {
        return res.status(403).send('Unauthorized');
    }

    const token = req.headers.authorization.split('Bearer ')[1];

    try {
        const decoded = await admin.auth().verifyIdToken(token);
        req.user = decoded;
    } catch (error) {
        return res.status(403).send('Unauthorized');
    }

    return next();
};

const gateway = new braintree.BraintreeGateway({
    environment: braintree.Environment.Sandbox,
    merchantId: 'rvnzpg6vvkkz25yb',
    publicKey: 'x3kbg83wxsk7cbks',
    privateKey: 'd51541b71ef34b6d0bd0c3f11d471441',
});

app.use(cors({ origin: true }));
app.use(authMiddleware);

app.get('/token', async (_, res) => {
    try {
        const { clientToken: token } = await gateway.clientToken.generate({});
        return res.status(200).send(JSON.stringify({ error: false, token }));
    } catch (error) {
        return res.status(400).send(JSON.stringify({ error: true, errorObj: error }));
    }
});

app.post('/checkout', async (req, res) => {
    let transactionErrors = {};
    const { amount, payment_method_nonce: nonce } = req.body;

    try {
        const response = await gateway.transaction.sale({
            amount,
            paymentMethodNonce: nonce,
            options: {
                submitForSettlement: true,
            },
        });
        if (response.success || response.transaction) {
            return res.status(200).send(JSON.stringify(response));
        }

        return res.status(400).send(JSON.stringify(response.errors.deepErrors()));
    } catch (error) {
        return res.status(400).send(JSON.stringify(transactionErrors));
    }
});

exports.widgets = functions.https.onRequest(app);
