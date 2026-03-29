const User = require('../models/User');
const Session = require('../models/Session');

exports.getAllUsers = async (req, res) => {
    try {
        const users = await User.find({}).select('-password');
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.getAllSessions = async (req, res) => {
    try {
        const sessions = await Session.find({}).populate('host', 'name email');
        res.json(sessions);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.deleteSession = async (req, res) => {
    try {
        const session = await Session.findById(req.params.id);
        if (!session) return res.status(404).json({ message: 'Session not found' });
        await session.remove();
        res.json({ message: 'Session deleted' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.deactivateUser = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) return res.status(404).json({ message: 'User not found' });
        // In a real app, you might set an 'active' flag. Here we just delete for simplicity
        await user.remove();
        res.json({ message: 'User deactivated' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};
