const mongoose = require('mongoose');

const messageSchema = new mongoose.Schema({
    session: { type: mongoose.Schema.Types.ObjectId, ref: 'Session', required: true },
    user: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
    userName: { type: String, required: true },
    text: { type: String, required: true },
    createdAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Message', messageSchema);
