const Session = require('../models/Session');

exports.createSession = async (req, res) => {
    const { title, subject, description, longitude, latitude, maxMembers } = req.body;
    try {
        const session = await Session.create({
            title,
            subject,
            description,
            location: {
                type: 'Point',
                coordinates: [longitude, latitude]
            },
            host: req.user._id,
            maxMembers,
            currentMembers: [req.user._id]
        });
        res.status(201).json(session);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.getNearbySessions = async (req, res) => {
    const { longitude, latitude } = req.query;
    try {
        const sessions = await Session.find({
            location: {
                $near: {
                    $geometry: { type: 'Point', coordinates: [parseFloat(longitude), parseFloat(latitude)] },
                    $maxDistance: 2000 // 2km radius
                }
            },
            status: 'active'
        }).populate('host', 'name rating');
        res.json(sessions);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.joinSession = async (req, res) => {
    try {
        const session = await Session.findById(req.params.id);
        if (!session) return res.status(404).json({ message: 'Session not found' });

        if (session.currentMembers.length >= session.maxMembers) {
            return res.status(400).json({ message: 'Session is full' });
        }

        if (session.currentMembers.includes(req.user._id)) {
            return res.status(400).json({ message: 'Already a member' });
        }

        session.currentMembers.push(req.user._id);
        if (session.currentMembers.length === session.maxMembers) {
            session.status = 'full';
        }
        await session.save();
        res.json(session);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.getSessionDetails = async (req, res) => {
    try {
        const session = await Session.findById(req.params.id).populate('host currentMembers', 'name rating');
        if (!session) return res.status(404).json({ message: 'Session not found' });
        res.json(session);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};
