class CreateMenu{

  private void createMenuBar() {
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem startMenuItem = new JMenuItem("Start spill");
    JMenuItem exitMenuItem = new JMenuItem("Lukk spill");
    file.add(startMenuItem);
    file.add(exitMenuItem);
    menubar.add(file);
    setJMenuBar(menubar);

     exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
              socket.close(); // i tillfelle den ikke blir lukke vanlig
            } catch (IOException e) {}
                    System.exit(0);
            }
        });

     startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              updater.talkToStockfish("position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 \n");
              updater.talkToStockfish("d \n");
            }
        });



}
