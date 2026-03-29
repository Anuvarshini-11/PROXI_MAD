const mongoose = require('mongoose');

const sessionSchema = new mongoose.Schema({
    title: { type: String, required: true },
    subject: { type: String, required: true },
    description: { type: String, required: true },
    location: {
        type: { type: String, default: 'Point' },
        coordinates: { type: [Number], required: true } // [lng, lat]
    },
    host: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
    maxMembers: { type: Number, required: true, min: 4, max: 6 },
    currentMembers: [{ type: mongoose.Schema.Types.ObjectId, ref: 'User' }],
    status: { type: String, enum: ['active', 'full', 'completed'], default: 'active' },
    createdAt: { type: Date, default: Date.now }
});

sessionSchema.index({ location: '2dsphere' });

module.exports = mongoose.model('Session', sessionSchema);
